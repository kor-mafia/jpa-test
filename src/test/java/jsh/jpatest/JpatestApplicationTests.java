package jsh.jpatest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class JpatestApplicationTests {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Test
    void 멤버_저장() {
        tx.begin();
        Member member = new Member();
        System.out.println("--------------------");
        em.persist(member);
        System.out.println("--------------------");
        Member findMember = em.find(Member.class, member.getId());

        Assertions.assertThat(member).isEqualTo(findMember);
        Assertions.assertThat(member).isSameAs(findMember);
        tx.commit();
    }
    
    @Test
    public void 팀_조회() {
        tx.begin();
        Member member = new Member();
        Team team = new Team();
        team.setName("coding");
        member.setTeam(team);

        em.persist(member);
        em.persist(team);

        member.getTeam().setName("jpa");

        Assertions.assertThat(team.getName()).isEqualTo(member.getTeam().getName());

        tx.commit();
    }

    @Test
    public void 팀의_멤버_변경은_안됨() {
        tx.begin();
        Member member = new Member();
        Team team = new Team();

        em.persist(member);
        em.persist(team);

        team.setName("coding");
        member.setTeam(team);


        team.getMembers().add(new Member());

        tx.commit();

        Assertions.assertThat(team.getMembers().size()).isEqualTo(1);
        Assertions.assertThat(team.getMembers().size()).isEqualTo(1);
    }
}
