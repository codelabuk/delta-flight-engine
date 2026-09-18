package io.github.codelabuk.deltaflight.planner;

import java.util.List;

public interface Predicate {

    static Predicate compare(String column, ComparisonOp op,
                             Comparable<?> literal) {
        return new Comparison(column, op, literal);
    }

    static Predicate and(Predicate... children){
        return new And(List.of(children));
    }

    static Predicate or(Predicate... children){
        return new Or(List.of(children));
    }

    record Comparison(String column, ComparisonOp op, Comparable<?> literal) implements Predicate {

        @Override
            public String toString() {
                return column + " " + op + " " + literal;
            }
        }

    record And(List<Predicate> children) implements Predicate {

        @Override
            public String toString() {
                return "(" + children.stream().map(Object::toString)
                        .reduce((a, b) -> a + "AND" + b).orElse("") + ")";
            }

        }

    record Or(List<Predicate> children) implements Predicate {

        @Override
            public String toString() {
                return "(" + children.stream().map(Object::toString)
                        .reduce((a, b) -> a + "OR" + b).orElse("") + ")";
            }

        }


}
