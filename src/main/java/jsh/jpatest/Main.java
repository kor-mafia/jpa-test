package jsh.jpatest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member member = new Member();
        Team team = new Team();

        em.persist(member);
        em.persist(team);

        team.setName("coding");
        member.setTeam(team);

        team.getMembers().add(new Member());
        team.getMembers().get(0).setName("jsh");

        member.setName("asdasd");

        tx.commit();
    }
}
