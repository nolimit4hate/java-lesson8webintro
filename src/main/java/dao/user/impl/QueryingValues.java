package dao.user.impl;

public enum QueryingValues {
    QUERY_ADD_USER(Group.EXEQUTE),
    QUERY_IS_USER_EXISTS(Group.EXEQUTE_QUERY),
    QUERY_SELECT_USERS(Group.EXEQUTE_QUERY);

    private Group group;

    QueryingValues(Group group){
        this.group = group;
    }

    public boolean isInGroup(Group group){
        return this.group == group;
    }

    public enum Group{
        EXEQUTE, EXEQUTE_QUERY;
    }
}
