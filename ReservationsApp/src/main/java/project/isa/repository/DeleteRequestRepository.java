package project.isa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.isa.model.DeleteRequest;

import java.util.List;

@Repository
public interface DeleteRequestRepository extends JpaRepository<DeleteRequest, Long> {
    List<DeleteRequest> findBySeenByAdminFalse();

    DeleteRequest findByUserUsername(String userUsername);



}
