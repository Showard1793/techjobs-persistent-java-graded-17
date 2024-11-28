package org.launchcode.givemeaquest.persistent.models.data;

import org.launchcode.givemeaquest.persistent.models.QuestTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestTagRepository extends CrudRepository<QuestTag, Integer> {
}
