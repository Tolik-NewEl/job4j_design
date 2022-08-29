package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(-1, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        int vert = box.getNumberOfVertices();
        assertThat(vert).isEqualTo(4)
                .isEven()
                .isNotZero();
    }

    @Test
    void isShapeExist() {
        Box box = new Box(8, 6);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void isShapeNotExist() {
        Box box = new Box(-1, 0);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void calcArea() {
        Box box = new Box(8, 12);
        double rsl = box.getArea();
        assertThat(rsl).isNotZero()
                .isEqualTo(864);
    }

    @Test
    void calcDouble() {
        Box box = new Box(4, 6);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(62.35d, withPrecision(0.01d))
                .isGreaterThan(62.35d)
                .isLessThan(62.36d);
    }
}