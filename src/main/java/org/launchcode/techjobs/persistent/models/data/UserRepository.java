package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    //custom query method
    User findByUsername(String username);
}
