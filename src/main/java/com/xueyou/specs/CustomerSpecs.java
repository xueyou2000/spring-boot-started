package com.xueyou.specs;

import com.xueyou.model.pojo.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/18
 */
public class CustomerSpecs {

    public static Specification<Customer> mySpec() {
        return  new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 混合条件查询
                // 模糊搜索taskName 并且createTime小于当前时间
                Path<String> taskName = root.get("taskName");
                Path<Date> createTime = root.get("createTime");
                return criteriaBuilder.and(criteriaBuilder.like(taskName, "%taskName%"), criteriaBuilder.lessThan(createTime, new Date()));
            }
        };
    }


}
