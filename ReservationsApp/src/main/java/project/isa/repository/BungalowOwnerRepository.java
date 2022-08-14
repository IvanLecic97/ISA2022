package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.users.BungalowOwner;

@Repository
public interface BungalowOwnerRepository extends JpaRepository<BungalowOwner, Long> {
    BungalowOwner findByUsername(String username);





}
