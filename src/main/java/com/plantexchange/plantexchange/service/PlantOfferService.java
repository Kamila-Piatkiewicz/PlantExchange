package com.plantexchange.plantexchange.service;

import com.plantexchange.plantexchange.model.OfferCategory;
import com.plantexchange.plantexchange.model.PlantOffer;
import com.plantexchange.plantexchange.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlantOfferService {

    @Autowired
    private EntityManager entityManager;

    public List<PlantOffer> findByQuery(OfferCategory category, List<Tag> tags, String city) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PlantOffer> criteriaQuery = criteriaBuilder.createQuery(PlantOffer.class);
        Root<PlantOffer> from = criteriaQuery.from(PlantOffer.class);

        List<Predicate> predicates = new ArrayList<>();
        if (category != null) {
            predicates.add(criteriaBuilder.equal(from.get("category"), category));
        }
        if (tags != null) {
            tags.stream()
                    .map(tag -> criteriaBuilder.isMember(tag, from.get("tags")))
                    .reduce(criteriaBuilder::and)
                    .ifPresent(predicates::add);
        }
        if (city != null) {
            predicates.add(criteriaBuilder.equal(from.get("city"), city));
        }

        criteriaQuery.select(from).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<PlantOffer> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
