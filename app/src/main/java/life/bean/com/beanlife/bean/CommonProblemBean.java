package life.bean.com.beanlife.bean;

/**
 * 作者 : bean on 2017/3/2/0002.
 * 注释 :
 */
public class CommonProblemBean {
    public String problem;
    public int arrowState;

    public CommonProblemBean(String problem, int arrowState) {
        this.problem = problem;
        this.arrowState = arrowState;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getArrowState() {
        return arrowState;
    }

    public void setArrowState(int arrowState) {
        this.arrowState = arrowState;
    }
}
