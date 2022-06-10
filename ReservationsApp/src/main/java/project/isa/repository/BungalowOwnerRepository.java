package project.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.isa.model.users.BungalowOwner;

public interface BungalowOwnerRepository extends JpaRepository<BungalowOwner, Long> {
}
