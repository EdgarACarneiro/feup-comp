package worker.workers;

import report.WorkerReport;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.AbstractFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import worker.Worker;

/**
 * Returns the number of lines of code in a class, this will both format into a generalized format for all Java code and remove comments
 */
public class W_linesOfCodeMethod extends Worker {
    public W_linesOfCodeMethod(CtElement rootNode, String patternName) {
        super(rootNode, patternName);
    }

    @Override
    protected AbstractFilter setFilter() {
        return new TypeFilter<>(CtMethod.class);
    }

    @Override
    public WorkerReport call() {
        return new WorkerReport(rootNode.toString().split("\r\n|\r|\n").length);
    }
}
