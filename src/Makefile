CC=cl.exe
CFLAGS=/EHsc
LD=cl.exe
LDFLAGS=
BUILD_DIR=..\build

DEBUG=

OBJS=$(BUILD_DIR)\jarvis.obj

all:$(BUILD_DIR)\jarvis.exe

$(BUILD_DIR)\jarvis.exe: $(OBJS)
	$(LD) $(LDFLAGS) $(DEBUG) /o $@ $(OBJS)

$(BUILD_DIR)\jarvis.obj: jarvis.cpp
	$(CC) $(CFLAGS) $(DEBUG) /Fo$(BUILD_DIR)\ -c jarvis.cpp

.PHONY: clean
clean:
	del /q $(BUILD_DIR)\*

rebuild:
	nmake clean
	nmake