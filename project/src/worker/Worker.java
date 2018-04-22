package worker;

import main.Configuration;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.Filter;
import util.Logger;

import java.util.concurrent.Callable;

/**
 * Class from which the Workers should inherit
 *
 * TODO
 * -> Todos os Workers devem ter uma interface comum que reporta resultados dinâmicos e estáticos do nó respetivo,
 * returna num Future de classe resultados.
 * -> Workers da classe devem poder lançar novos workers, e só retornar quando os workers filho retornam,
 * reportanto a soma dos resutlados deles.d
 */
public abstract class Worker implements Callable {
    Configuration configuration;
    Filter filter; // the filter that, upon true, should trigger this worker
    Logger logger;
    CtElement element;

    public Worker(Configuration configuration, CtElement element) {
        this.configuration = configuration;
        this.element = element;
        logger = new Logger(this);
        this.setFilter();
    }

    /**
     * method called by constructor to set the filter variable, without which Workers do not work
     */
    protected abstract void setFilter();

    /**
     * method that uses the filter to test a given CtElement
     *
     * @param c the CtElement to test against the Filter
     * @return true if there is a match
     */
    public boolean matches(CtElement c) { return filter.matches(c); }

}
