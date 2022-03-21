package tis.oauth2server.client.repo;


import org.springframework.data.repository.CrudRepository;
import tis.oauth2server.client.entity.OAuthClientEntity;

public interface ClientRepository extends CrudRepository<OAuthClientEntity, Long> {
    OAuthClientEntity findFirstByUid(String uid);
    OAuthClientEntity findFirstByClientId(String clientId);
}
