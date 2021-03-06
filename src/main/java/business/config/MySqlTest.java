package business.config;


import business.entity.Department;
import business.entity.Role;
import business.entity.User;
import business.interf.DepartmentRepository;
import business.interf.RoleRepository;
import business.interf.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;


/**
 * Created by Lemostic on 2017/10/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfiguration.class)
public class MySqlTest {
    private static Logger logger = LoggerFactory.getLogger(MySqlTest.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.notNull(department.getId(),"department's id is required!");

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId(),"role's id is required!");

        User user = new User();
        user.setName("user");
        userRepository.save(user);
        Assert.notNull(user.getId(),"user's id is required!");

        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles,"roles is required!");
        user.setRoles(roles);

        userRepository.save(user);
        Assert.notNull(user.getId(),"user's id is required!");
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page,"page is required!");

        for (User user : page.getContent()){
            logger.info("user.name:{},user.id:{}",user.getName(),user.getId());
        }
    }
}
