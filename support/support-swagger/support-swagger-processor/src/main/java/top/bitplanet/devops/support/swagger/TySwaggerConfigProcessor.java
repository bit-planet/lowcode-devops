package top.bitplanet.devops.support.swagger;

import com.squareup.javapoet.*;
import top.bitplanet.devops.support.core.helper.InitialHelper;
import org.springframework.stereotype.Component;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;


@SupportedAnnotationTypes({"top.bitplanet.devops.support.swagger.TyEnableOpenApi"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TySwaggerConfigProcessor extends AbstractProcessor {

	private Messager messager; // Log 日志
	private Elements elementUtils; // 操作Element工具类
	private Filer filer; // 支持通过注解处理器创建新文件
	private Map<String, String> options; // 额外配置参数

	@Override
	public synchronized void init(ProcessingEnvironment processingEnvironment) {
		super.init(processingEnvironment);
		messager = processingEnvironment.getMessager();
		elementUtils = processingEnvironment.getElementUtils();
		filer = processingEnvironment.getFiler();
		options = processingEnvironment.getOptions();
	}



	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		System.out.println("自定义注解 ========> @TyEnableOpenApi");
		if (!annotations.isEmpty()){
			//获取Bind注解类型的元素，这里是类类型TypeElement
			Set<? extends Element> bindElement = roundEnv.getElementsAnnotatedWith(TyEnableOpenApi.class);
			try {
				generateCode(bindElement);
			} catch (Exception e) {
				System.out.println("！！！自定义注解@TyEnableOpenApi编译报错");
				e.printStackTrace();
			}
			return true;
		}

		return false;
	}

	private void generateCode(Set<? extends Element> elements) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		for (Element element : elements){
			//由于是在类上注解，那么获取TypeElement
			TypeElement typeElement = (TypeElement) element;
			//获取全限定类名
			String className = typeElement.getQualifiedName().toString();
			System.out.println("className:" + className);
			//获取包路径
			PackageElement packageElement = elementUtils.getPackageOf(typeElement);
			String packageName = packageElement.getQualifiedName().toString();
			System.out.println("packageName:" + packageName);
			//获取用于生成的类名
			className = getClassName(typeElement,packageName);
			//获取注解值
			TyEnableOpenApi bindAnnotation = typeElement.getAnnotation(TyEnableOpenApi.class);
			String title = bindAnnotation.title();
			System.out.println("title:" + title);
			//生成的类
			TypeSpec.Builder builder = TypeSpec
					.classBuilder("TySwaggerApiInfo$$")
					.addModifiers(Modifier.PUBLIC)
					.addAnnotation(Component.class)
					//.addField(title)
					.superclass(TySwaggerApiInfo.class);
			Method[] methods = TySwaggerApiInfo.class.getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.startsWith("get") && !"getClass".equals(methodName)) {
					System.out.println(method.getName());
					System.out.println(method.getReturnType());
					MethodSpec.Builder methodSpec = MethodSpec
							.methodBuilder(methodName)
							.addModifiers(Modifier.PUBLIC)
//					.addParameter(String.class,"title")
							.returns(method.getReturnType());
					// 去掉get 首字母小写
					String prop = InitialHelper.toLowerCase(methodName.substring(3));
					Method annoMethod = TyEnableOpenApi.class.getMethod(prop);
					Object invoke = annoMethod.invoke(bindAnnotation);
					//$L表示字面量 $T表示类型 $S表示字符串
					if (invoke instanceof String) {
						methodSpec.addStatement("return $S",invoke);
					} else {
						methodSpec.addStatement("return $L",invoke);
					}
					builder.addMethod(methodSpec.build());
				}
			}
			//创建javaFile文件对象
			JavaFile javaFile = JavaFile.builder(packageName,builder.build()).build();
			//写入源文件
			javaFile.writeTo(filer);
		}
	}

	/**
	 * 根据type和package获取类名
	 * @param type
	 * @param packageName
	 * @return
	 */
	private static String getClassName(TypeElement type, String packageName) {
		int packageLen = packageName.length() + 1;
		return type.getQualifiedName().toString().substring(packageLen)
				.replace('.', '$');
	}

	public static void main(String[] args) throws NoSuchMethodException {
		Method[] methods = TyEnableOpenApi.class.getMethods();
		for (Method method : methods) {

		}

		Method title = TyEnableOpenApi.class.getMethod("title");
	}

}