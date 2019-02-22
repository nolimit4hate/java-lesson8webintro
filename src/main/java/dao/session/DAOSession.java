package dao.session;

import dao.exceptions.DAOEntryAlreadyExistsException;
import dao.exceptions.DAONoSuchEntityException;
import dao.exceptions.DAOSystemException;
import model.UserRole;
import model.session.UserSession;

import java.util.List;

public interface DAOSession {
    List<UserSession> getAllSessions()throws DAOSystemException, DAONoSuchEntityException;
    UserSession getSessionById(String id)throws DAOSystemException, DAONoSuchEntityException;
    UserSession getSessionByUserName(String userName) throws DAOSystemException, DAONoSuchEntityException;
    void addSessionToPool(UserSession userSession) throws DAOSystemException, DAOEntryAlreadyExistsException;
    void deleteSessionById(String id) throws DAOSystemException, DAONoSuchEntityException;
    boolean isExistSessionByIdIp(String id, String ip) throws DAOSystemException;
    UserRole getUserRoleBySessionId(String id) throws DAOSystemException, DAONoSuchEntityException;

}
