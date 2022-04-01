package kr.starbridge.web.domain.member.repository;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    boolean existsByName(String name);

    boolean existsByIdAndPw(String id, String pw);

    Optional<MemberEntity> findByIdAndPw(String id, String pw);
}
