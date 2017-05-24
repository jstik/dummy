package tenant;

import org.springframework.context.annotation.Scope;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julia on 08.05.2017.
 */
@Repository
public interface TenantRepository extends PagingAndSortingRepository<Tenant, Long> {

    Tenant findTenantByName(String name);
}
