package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void saveItem() {
        //g
        Item item1 = new Item();
        item1.setName("가방");

        //w
        itemService.saveItem(item1);

        //t
        Assertions.assertEquals(item1, itemRepository.findOne(item1.getId()));
    }

    @Test
    public void findItems() {
        //g
        Item item1 = new Item();
        item1.setName("가방");
        Item item2 = new Item();
        item2.setName("신발");
        itemService.saveItem(item1);
        itemService.saveItem(item2);

        //w
        List<Item> items = itemService.findItems();


        //t
        Assertions.assertEquals(2, items.size());
    }

    @Test
    public void findOne() {
    }
}