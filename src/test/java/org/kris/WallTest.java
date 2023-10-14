package org.kris;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @ParameterizedTest
    @MethodSource("provideWallsForFindByColor")
    void testFindBlockByColor(Structure wall, String color, boolean found) {
        Optional<Block> result = wall.findBlockByColor(color);

        if (found) {
            String message = "Couldn't find any block by provided color";
            assertTrue(result.isPresent(), message);
            assertEquals(color.toLowerCase(), result.get().getColor().toLowerCase(), message);
        } else {
            assertFalse(result.isPresent(), "No block should be found for the provided color");
        }
    }



    private static Structure buildWall() {
        CompositeBlock compositeBlock = new CompositeBlockImpl("black", "mix");
        compositeBlock.addBlock(new WoodenBlock("brown", "oak"));
        compositeBlock.addBlock(new StoneBlock("white", "onyx"));
        return new Wall(
                List.of(
                        new WoodenBlock("red", "oak"),
                        new StoneBlock("gray", "andesite"),
                        compositeBlock
                )
        );
    }

    private static Stream<Arguments> provideWallsForFindByColor() {
        Structure wallWithGrayAndWhite = buildWall();
        Structure emptyWall = new Wall(new ArrayList<>());

        return Stream.of(
                Arguments.of(wallWithGrayAndWhite, "gray", true),
                Arguments.of(wallWithGrayAndWhite, "white", true),
                Arguments.of(wallWithGrayAndWhite, "whiTE", true),
                Arguments.of(wallWithGrayAndWhite, "black", true),
                Arguments.of(wallWithGrayAndWhite, "pink", false),
                Arguments.of(emptyWall, "white", false)
        );
    }

}
