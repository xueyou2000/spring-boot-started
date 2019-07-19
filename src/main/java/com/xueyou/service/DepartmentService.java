package com.xueyou.service;

import com.xueyou.model.pojo.Department;
import com.xueyou.model.pojo.User;
import com.xueyou.repository.DepartmentRepository;
import com.xueyou.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * 创建 by xueyo on 2019/7/19
 */
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final UserRepository userRepository;

    /**
     * 构造函数
     * @param departmentRepository  部门仓储
     * @param userRepository
     */
    public DepartmentService(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    /**
     * 新增部门
     * @param department    部门实体
     * @return  新增的部门
     */
    public Department create(Department department) {
        department.setCreateDate(new Date());
        return departmentRepository.save(department);
    }

    /**
     * 根据部门名称新增部门
     * @param deptName  部门名称
     * @return  新增的部门
     */
    public Department createByName(String deptName) {
        Department department = new Department();
        department.setDeptName(deptName);
        return create(department);
    }

    /**
     * 创建部门及相关用户
     * @param deptName  部门名称
     * @param userList  用户集合
     * @return  新增的部门
     */
    @Transactional
    public Department createByNameWithUsers(String deptName, List<User> userList) {
        Department department = createByName(deptName);
        userList.forEach(user -> user.setDepartment(department));
        userRepository.saveAll(userList);
        return department;
    }

}
