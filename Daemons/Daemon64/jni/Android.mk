LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := Daemon64

LOCAL_CPPFLAGS := -DNDEBUG
LOCAL_CFLAGS += -DNDEBUG

ifeq ($(TARGET_ARCH_ABI), arm64-v8a)
	LOCAL_SRC_FILES := backend/plugin64.cpp

	LOCAL_CPP_INCLUDES += $(LOCAL_PATH)
	LOCAL_CPP_INCLUDES += $(LOCAL_PATH)/backend
endif

LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -lz -llog

include $(BUILD_EXECUTABLE)


#<============Native lib===========>

include $(CLEAR_VARS)

LOCAL_MODULE := liblibpg64-lib

LOCAL_CPPFLAGS := -DNDEBUG
LOCAL_CFLAGS += -DNDEBUG

ifeq ($(TARGET_ARCH_ABI), arm64-v8a)
	LOCAL_SRC_FILES := users/plugin64.cpp

    LOCAL_CPP_INCLUDES += $(LOCAL_PATH)
	LOCAL_CPP_INCLUDES += $(LOCAL_PATH)/users

endif

LOCAL_LDFLAGS += -L$(SYSROOT)/usr/lib -lz -llog

include $(BUILD_SHARED_LIBRARY)




