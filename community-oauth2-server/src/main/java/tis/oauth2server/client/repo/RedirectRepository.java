package tis.oauth2server.client.repo;


import org.springframework.data.repository.CrudRepository;
import tis.oauth2server.client.entity.OAuthClientEntity;
import tis.oauth2server.client.entity.RedirectEntity;

import java.util.List;

public interface RedirectRepository extends CrudRepository<RedirectEntity, Long> {
    List<RedirectEntity> findAllByClient(OAuthClientEntity client);
}
