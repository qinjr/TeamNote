package dao.mysqlDaoImpl;

import dao.mysqlDao.NotebookInfoDao;
import model.mysql.NotebookInfo;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjr on 2017/6/30.
 */
public class NotebookInfoDaoImpl extends HibernateDaoSupport implements NotebookInfoDao  {
    public Integer addNotebookInfo(NotebookInfo notebookInfo) {
        return (Integer) getHibernateTemplate().save("notebookInfo", notebookInfo);
    }

    public void deleteNotebookInfo(NotebookInfo notebookInfo) {
        getHibernateTemplate().delete(notebookInfo);
    }

    public void updateNotebookInfo(NotebookInfo notebookInfo) {
        getHibernateTemplate().merge(notebookInfo);
    }

    public NotebookInfo getNotebookInfoById(int notebookId) {
        List<NotebookInfo> notebookInfos = (List<NotebookInfo>) getHibernateTemplate().find(
                "from NotebookInfo as n where n.notebookId=?", notebookId);
        return notebookInfos.size() > 0 ? notebookInfos.get(0) : null;
    }

    public ArrayList<NotebookInfo> getAllNotebookInfos() {
        ArrayList<NotebookInfo> notebookInfos = (ArrayList<NotebookInfo>) getHibernateTemplate().find(
                "from NotebookInfo");
        return notebookInfos;
    }
}
