package org.launchcode.givemeaquest.persistent.models.data;

import org.launchcode.givemeaquest.persistent.models.Quest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends CrudRepository<Quest, Integer> {
}
