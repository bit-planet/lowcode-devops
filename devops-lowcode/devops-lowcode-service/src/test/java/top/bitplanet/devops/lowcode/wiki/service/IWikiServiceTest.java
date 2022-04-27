package top.bitplanet.devops.lowcode.wiki.service;

import top.bitplanet.devops.lowcode.wiki.entity.Wiki;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IWikiServiceTest {

    @Autowired
    private IWikiService wikiService;

    @Test
    public void testGetById() {
        List<Wiki> list = wikiService.list();
        System.out.println(list);

    }
}