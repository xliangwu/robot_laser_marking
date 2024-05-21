<!--
 * @Author: 秦少卫
 * @Date: 2022-09-03 19:16:55
 * @LastEditors: 秦少卫
 * @LastEditTime: 2024-05-11 15:49:01
 * @LastEditors: 秦少卫
 * @LastEditTime: 2023-04-10 14:33:18
 * @Description: 保存文件
-->

<template>
  <div class="save-box">
    <Button style="margin-left: 10px" type="text" @click="beforeClear">
      {{ $t('save.empty') }}
    </Button>

    <Button style="margin-left: 10px" type="primary" @click="saveUserDesignerImage">
      {{ $t('save.copy_to_clipboardstr') }}
    </Button>

    <!-- <Dropdown style="margin-left: 10px" @on-click="saveWith">
      <Button type="primary">
        {{ $t('save.down') }}
        <Icon type="ios-arrow-down"></Icon>
      </Button>
      <template #list>
        <DropdownMenu>
          <DropdownItem name="saveImg" divided>{{ $t('save.save_as_picture') }}</DropdownItem>
          <DropdownItem name="saveSvg">{{ $t('save.save_as_svg') }}</DropdownItem>
          <DropdownItem name="clipboardBase64">{{ $t('save.copy_to_clipboardstr') }}</DropdownItem>
        </DropdownMenu>
      </template>
</Dropdown> -->
  </div>

  <Modal v-model="showStartMarkingWarningDialog" width="400">
    <template #header>
      <p style="color: crimson; text-align: center">
        <Icon type="ios-information-circle"></Icon>
        <span>下单通知</span>
      </p>
    </template>
    <div style="text-align: center">
      <h3>正在标刻中，请打标完成再下单。</h3>
    </div>
    <template #footer>
      <Button type="error" size="large" @click="closeMarkingWarningDialog">确定</Button>
    </template>
  </Modal>

  <Modal v-model="showStartMarkingConfirmDialog" title="下单确认" @on-ok="confirmStartMarking">
    <div style="text-align: left">
      <h3>即将下单，请您确认下单。</h3>
    </div>
  </Modal>
</template>

<script setup name="save-bar">
import { startMarking, queryMarkingStatus } from '@/api/user';

import { Modal } from 'view-ui-plus';
import useSelect from '@/hooks/select';
import useMaterial from '@/hooks/useMaterial';
import { debounce } from 'lodash-es';
import { useI18n } from 'vue-i18n';
import { Spin } from 'view-ui-plus';
import { useRoute } from 'vue-router';
import { Message } from 'view-ui-plus';
const route = useRoute();

const { createTmplByCommon, updataTemplInfo, routerToId } = useMaterial();

const { t } = useI18n();

const { canvasEditor } = useSelect();

//data
const showStartMarkingWarningDialog = ref(false);
const showStartMarkingConfirmDialog = ref(false);

const cbMap = {
  clipboard() {
    canvasEditor.clipboard();
  },
  saveJson() {
    canvasEditor.saveJson();
  },
  saveSvg() {
    canvasEditor.saveSvg();
  },
  saveImg() {
    canvasEditor.saveImg();
  },

  clipboardBase64() {
    // canvasEditor.clipboardBase64();
    var callback = function (imageData, type) {
      var request = {
        imageData: imageData,
        type: type,
      };
      startMarking(request);
    };
    canvasEditor.saveLocal(callback);
  },

  async saveMyClould() {
    try {
      Spin.show();
      if (route?.query?.id) {
        await updataTemplInfo(route?.query?.id);
      } else {
        const res = await createTmplByCommon();
        routerToId(res.data.data.id);
      }
    } catch (error) {
      Message.warning('请登录');
    }
    Spin.hide();
  },
};

const saveWith = debounce(function (type) {
  cbMap[type] && typeof cbMap[type] === 'function' && cbMap[type]();
}, 300);

/**
 * @desc clear canvas 清空画布
 */
const clear = () => {
  canvasEditor.clear();
};

const beforeClear = () => {
  Modal.confirm({
    title: t('tip'),
    content: `<p>${t('clearTip')}</p>`,
    okText: t('ok'),
    cancelText: t('cancel'),
    onOk: () => clear(),
  });
};

const saveUserDesignerImage = () => {
  Spin.show({
    render: (h) => h('h1', '正在查询下单状态，请耐心等待'),
  });

  queryMarkingStatus()
    .then((res) => {
      if (res.status == 200 && res.data?.code == 0) {
        var markingStatus = res.data?.data;
        if (markingStatus == 1 || markingStatus == -1 || markingStatus == 2) {
          showStartMarkingWarningDialog.value = true;
        } else if (markingStatus == 3 || markingStatus == 0) {
          showStartMarkingConfirmDialog.value = true;
        }
      } else {
        this.$Message['error']({
          background: true,
          content: '这是一条带背景色的通知',
        });
      }
    })
    .finally(() => {
      Spin.hide();
    });
};

const confirmStartMarking = () => {
  var callback = function (imageData, type) {
    var request = {
      imageData: imageData,
      type: type,
    };

    startMarking(request).then((res) => {
      console.log(res);
      showStartMarkingConfirmDialog.value = false;
      Message.success({
        content: '下单成功',
        duration: 5,
        background: true,
      });
      canvasEditor.clear();
    });
  };
  canvasEditor.saveLocal(callback);
};

const closeMarkingWarningDialog = () => {
  showStartMarkingWarningDialog.value = false;
};
</script>

<style scoped lang="less">
.save-box {
  display: inline-block;
  padding-right: 10px;
}
</style>
