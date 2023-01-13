package ru.otus.homework.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.enums.ResourceType;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Должен обрабатывать исключение ошибки ")

class ObjectParserTest {

   private String text;
   private int currentLine;

   @BeforeEach
   void setUp(){
       text = "десять";
       currentLine = 10;
   }


    @DisplayName("парсинга строки с не числовым значением в Long")
    @Test
    void shouldParseHandleExceptions(){
        assertThrows(RuntimeException.class,
                () -> ObjectParser.toLong(text, currentLine, ResourceType.TASK));
    }


}