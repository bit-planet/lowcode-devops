/*
 *
 *  Copyright 2017-2019 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package springfox.documentation.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import springfox.documentation.service.VendorExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.*;

/**
 *  修复swagger3.0 启动空指针异常，class覆盖源码
 * <p>
 *  @TODO  swagger java 暂停维护，后期考虑替代品
 * </p>
 *
 * @author Le
 * @since 2021-12-10
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Example {
  private final String id;
  private final String summary;
  private final String description;
  private final Object value;
  private final String externalValue;
  private final String mediaType;
  private final List<VendorExtension> extensions = new ArrayList<>();

  /**
   * @deprecated @since 3.0.0
   * Use @see {@link Example#Example(String, String, String, Object, String, String)}
   * @param value - example literal
   */
  @Deprecated
  public Example(Object value) {
    this.value = value;
    this.mediaType = null;
    externalValue = null;
    id = null;
    summary = null;
    description = null;
  }

  /**
   * @deprecated @since 3.0.0
   * Use @see {@link Example#Example(String, String, String, Object, String, String)}
   * @param mediaType - media type of the example
   * @param value - example literal
   */
  @Deprecated
  public Example(
      String mediaType,
      Object value) {
    this.mediaType = mediaType;
    this.value = value;
    externalValue = null;
    id = null;
    summary = null;
    description = null;
  }

  public Example(
      String id,
      String summary,
      String description,
      Object value,
      String externalValue,
      String mediaType) {
    this.id = id;
    this.summary = summary;
    this.description = description;
    this.value = value;
    this.externalValue = externalValue;
    this.mediaType = mediaType;
  }

  public String getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public String getExternalValue() {
    return externalValue;
  }

  public List<VendorExtension> getExtensions() {
    return extensions;
  }

  public Object getValue() {
    return value;
  }

  public Optional<String> getMediaType() {
    return ofNullable(mediaType);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Example example = (Example) o;
    // 解决启动空指针异常
    return Objects.equals(id,example.id) &&
        Objects.equals(summary, example.summary) &&
        Objects.equals(description, example.description) &&
        Objects.equals(value, example.value) &&
        Objects.equals(externalValue,example.externalValue) &&
        Objects.equals(mediaType,example.mediaType) &&
        Objects.equals(extensions,example.extensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        summary,
        description,
        value,
        externalValue,
        mediaType,
        extensions);
  }
}
