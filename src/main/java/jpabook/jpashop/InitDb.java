package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 주문 2개
 * userA
 *  JPA1 Book
 *  JPA2 Book
 *
 * userB
 *  SPRING1 BOOK
 *  SPRING2 BOOK
 *
 */
@Component
@RequiredArgsConstructor
public class InitDb {

  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.dbInit1();
    initService.dbInit2();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {

    private final EntityManager em;

    public void dbInit1() {
      Member member = createMember("userA", "서울", "1", "1111");
      em.persist(member);

      Book book1 = createBook("JPA1 BOOK", 20000, 200);
      em.persist(book1);

      Book book2 = createBook("JPA2 BOOK", 40000, 300);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 2);

      Order order = createDelivery(member, orderItem1, orderItem2);
      em.persist(order);
    }

    public void dbInit2() {
      Member member = createMember("userB", "서울", "1", "1111");
      em.persist(member);

      Book book1 = createBook("SPRING1 BOOK", 10000, 100);
      em.persist(book1);

      Book book2 = createBook("SPRING2 BOOK", 20000, 100);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 3);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 4);

      Order order = createDelivery(member, orderItem1, orderItem2);
      em.persist(order);
    }

    private Book createBook(String name, int price, int stockQuantity) {
      Book book1 = new Book();
      book1.setName(name);
      book1.setPrice(price);
      book1.setStockQuantity(stockQuantity);
      return book1;
    }

    private Member createMember(String name, String city, String street, String zipcode) {
      Member member = new Member();
      member.setName(name);
      member.setAddress(new Address(city, street, zipcode));
      return member;
    }

    private Order createDelivery(Member member, OrderItem orderItem1, OrderItem orderItem2) {
      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      return Order.createOrder(member, delivery, orderItem1, orderItem2);
    }
  }
}
