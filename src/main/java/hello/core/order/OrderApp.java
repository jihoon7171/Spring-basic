package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberservice = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "jihoon", Grade.VIP);
        memberservice.join(member);

        Order order = orderService.createOrder(memberId, "item1", 10000);
        System.out.println("order = " +order);
    }
}
