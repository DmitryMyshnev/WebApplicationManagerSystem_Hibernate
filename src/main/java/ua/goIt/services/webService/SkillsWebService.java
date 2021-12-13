package ua.goIt.services.webService;

import ua.goIt.dao.DeveloperDao;
import ua.goIt.dao.SkillDao;
import ua.goIt.model.Skill;

import java.util.List;
import java.util.Optional;

public class SkillsWebService implements CrudWeb<Skill> {
    private static SkillsWebService skillsWebService;
    private final SkillDao skillDao;
    private final DeveloperDao developerDao;

    private SkillsWebService() {
        skillDao = SkillDao.getInstance();
        developerDao = DeveloperDao.getInstance();
    }

    @Override
    public void save(Skill entity) {
        skillDao.create(entity);
    }

    @Override
    public void update(Skill entity) {
        skillDao.update(entity);
    }

    @Override
    public void delete(Skill entity) {
        skillDao.delete(entity);
    }

    @Override
    public List<Skill> getAll() {
        return skillDao.getAll();
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillDao.getById(id);
    }

    public static SkillsWebService getInstance() {
        if (skillsWebService == null) {
            skillsWebService = new SkillsWebService();
        }
        return skillsWebService;
    }
}
