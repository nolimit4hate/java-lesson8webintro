package dao.session.impl;

import dao.exceptions.DAOEntryAlreadyExistsException;
import dao.exceptions.DAONoSuchEntityException;
import dao.exceptions.DAOSystemException;
import dao.session.DAOSession;
import model.UserRole;
import model.session.UserSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionsTemp implements DAOSession {
    private final Map<Integer, UserSession> sessionsPool= new HashMap<>();
    private Integer sessionsCount;

    public SessionsTemp() {
        this.sessionsCount = 0;
    }

    @Override
    public List<UserSession> getAllSessions() throws DAOSystemException, DAONoSuchEntityException {
        isEmptySessions();
        List<UserSession> result = new ArrayList<>();
        sessionsPool.forEach((number, currentSession) -> {
            result.add(currentSession);
        });
        return result;
    }

    @Override
    public UserSession getSessionById(String id) throws DAOSystemException, DAONoSuchEntityException {
        return null;
    }

    @Override
    public UserSession getSessionByUserName(String userName) throws DAOSystemException, DAONoSuchEntityException {
        return null;
    }

    @Override
    public void addSessionToPool(UserSession userSession) throws DAOSystemException, DAOEntryAlreadyExistsException {
        if(sessionsPool.isEmpty()) {
            sessionsPool.put(sessionsCount, userSession);
            sessionsCount++;
        }
        for(Map.Entry<Integer, UserSession> entry : sessionsPool.entrySet()){
            String entryID = entry.getValue().getSessionID();
            String entryIP = entry.getValue().getUserIP();
            if(entryID.equals(userSession.getSessionID())){

            }
        }
    }

    @Override
    public void deleteSessionById(String id) throws DAOSystemException, DAONoSuchEntityException {
        isEmptySessions();
        for(Map.Entry<Integer, UserSession> entry : sessionsPool.entrySet()){
            String entryID = entry.getValue().getSessionID();
            int entryKey = entry.getKey();
            if(entryID.equals(id)){
                sessionsPool.remove(entryKey);
            }
        }
        throw new DAONoSuchEntityException("ERROR: NO SUCH ENTITY={" + id + "}");
    }

    @Override
    public boolean isExistSessionByIdIp(String id, String ip) throws DAOSystemException {
        if(sessionsPool.isEmpty())
            return false;
        for(Map.Entry<Integer, UserSession> entry : sessionsPool.entrySet()){
            String entryID = entry.getValue().getSessionID();
            String entryIP = entry.getValue().getUserIP();
            if(entryID.equals(id) && entryIP.equals(ip))
                return true;
        }
        return false;
    }

    @Override
    public UserRole getUserRoleBySessionId(String id) throws DAOSystemException, DAONoSuchEntityException {
        isEmptySessions();
        for(Map.Entry<Integer, UserSession> entry : sessionsPool.entrySet()){
            String entryID = entry.getValue().getSessionID();
            if(entryID.equals(id))
                return entry.getValue().getUserRole();
        }
        throw new DAONoSuchEntityException("ERROR: NO ENTITY={" + id + "}");
    }

    private void isEmptySessions() throws DAONoSuchEntityException {
        if(sessionsPool.isEmpty())
            throw new DAONoSuchEntityException("ERROR: NO ENTITY");
    }
}
