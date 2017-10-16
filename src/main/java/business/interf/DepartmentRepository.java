package business.interf;

import business.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lemostic on 2017/10/11.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
