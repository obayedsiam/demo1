package com.example.studentCrud.helper;

import com.example.studentCrud.service.BaseService;
import com.example.studentCrud.utils.PaginationParameters;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class GetListHelper<T> extends BaseService {
    final Class<T> typeParameterClass;
    private final EntityManager em;

    public GetListHelper(EntityManager em, Class<T> typeParameterClass) {
        this.em = em;
        this.typeParameterClass = typeParameterClass;
    }

    public Map<String, Object> getList(Integer page, Integer size) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<T> result = tQuery.setFirstResult(Math.max((page - 1), 0) * size)
                .setMaxResults(size)
                .getResultList();

        Map<String, Object> maps = new HashMap<>();
        PaginationParameters.getData(maps, page, total, size, result);

        return maps;
    }
}
