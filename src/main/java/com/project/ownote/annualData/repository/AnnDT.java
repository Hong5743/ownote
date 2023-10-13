package com.project.ownote.annualData.repository;

import com.project.ownote.annualData.dto.AnnData;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AnnDT {
    @PersistenceContext
    EntityManager em;

    public List<AnnData> findAll(){
        String sql ="select m from AnnData m";
        TypedQuery<AnnData> query = em.createQuery(sql, AnnData.class);

        List<AnnData>list= query.getResultList();
        return list;
    }

    public List<AnnData> selectListAll(@Param("emp_id") int emp_id) {
        String sql = "SELECT m FROM AnnData m WHERE emp_id = :emp_id";
        TypedQuery<AnnData> query = em.createQuery(sql, AnnData.class);
        query.setParameter("emp_id", emp_id); // ":emp_id" 파라미터 설정
        List<AnnData> list = query.getResultList();
        return list;
    }
}
