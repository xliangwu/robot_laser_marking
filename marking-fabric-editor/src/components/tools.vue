<template>
  <div>
    <Divider plain orientation="left">{{ $t('common_elements') }}</Divider>
    <div class="tool-box">
      <span @click="() => addText()" :draggable="true" @dragend="onDragend('text')">
        <svg
          t="1650875455324"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="5401"
          width="26"
          height="26"
        >
          <path
            d="M213.333333 209.92v128h85.333334v-42.666667h170.666666v433.493334H384.853333v85.333333h256v-85.333333H554.666667V295.253333h170.666666v42.666667h85.333334v-128H213.333333z"
            p-id="5402"
          ></path>
        </svg>
      </span>
      <span @click="() => addTextBox()" :draggable="true" @dragend="onDragend('textBox')">
        <svg
          t="1650854954008"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="14038"
          width="26"
          height="26"
        >
          <path
            d="M720.832 692.352h-12.64L530.208 260.448a19.968 19.968 0 0 0-36.416 0L316.608 692.352h-13.44c-7.904 0-15.04 3.968-17.408 11.072a19.872 19.872 0 0 0 18.208 27.68h56.96c9.504 0 18.208-6.336 19.776-15.808a18.752 18.752 0 0 0-15.808-21.344l36.384-87.808h159.776l34.816 87.808a18.88 18.88 0 0 0-15.808 21.344c1.568 9.504 10.272 15.808 19.776 15.808h121.024c7.904 0 15.04-3.968 17.408-11.072a19.2 19.2 0 0 0-17.408-27.68z m-306.112-125.76l64.864-158.208 64.864 158.208H414.72z m-246.816-80.704c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m0-75.936c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m0 151.872c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m11.872-216.736a12.16 12.16 0 0 0 11.872-11.872v-23.744c-0.8-6.336-5.536-11.072-11.872-11.072s-11.872 5.536-11.872 11.872v23.744c0 6.336 4.736 11.072 11.872 11.072z m-11.872 292.672c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m0 75.936c0 7.104 4.736 11.872 11.872 11.872a12.16 12.16 0 0 0 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m665.248-227.808c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m0-75.936c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m0 151.872c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m11.072-216.736a12.16 12.16 0 0 0 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744c0.8 7.104 5.536 11.872 11.872 11.872z m-11.072 292.672c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m0 75.936c0 6.336 5.536 11.872 11.872 11.872s11.872-5.536 11.872-11.872v-23.744c0-6.336-5.536-11.872-11.872-11.872s-11.872 5.536-11.872 11.872v23.744z m-347.264 119.456h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872z m-75.936 0h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872z m151.872 0h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872z m-228.608 0h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872h23.744c6.336 0 11.872-5.536 11.872-11.872s-4.736-11.872-11.872-11.872z m-75.136-7.904H222.496v-11.072a12.48 12.48 0 0 0-12.672-12.64h-18.976v-37.984c0-7.104-5.536-12.64-11.872-12.64s-11.872 5.536-11.872 12.64v37.984h-10.272a12.512 12.512 0 0 0-12.672 12.64v52.992a12.48 12.48 0 0 0 12.672 12.64h52.992a12.512 12.512 0 0 0 12.672-12.64v-11.072h35.584c7.104-1.568 12.672-7.904 12.672-14.24 0-7.104-12.672-16.608-12.672-16.608z m379.68 7.904h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872z m75.936 0h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872h23.744c6.336 0 11.872-5.536 11.872-11.872s-4.736-11.872-11.872-11.872z m-251.52-642.304h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872z m-75.936 0h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872z m151.872 0h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872z m-227.808 0h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872h-23.744c-7.104 0.8-11.872 5.536-11.872 12.672s4.736 11.072 11.872 11.072zM257.28 167.904H221.696v-11.072a12.512 12.512 0 0 0-12.672-12.672H156.832a12.512 12.512 0 0 0-12.672 12.672v52.992c0 7.104 5.536 12.672 12.672 12.672h11.072v35.584c1.568 7.104 7.904 12.672 14.24 12.672s16.608-12.672 16.608-12.672V222.496h11.072a12.512 12.512 0 0 0 12.672-12.672v-18.976h35.584a12.16 12.16 0 0 0 11.872-11.872 12.224 12.224 0 0 0-12.672-11.072z m356.768 22.944h23.744c6.336 0 11.872-5.536 11.872-11.872s-5.536-11.872-11.872-11.872h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872z m76.736 0h23.744c6.336 0 11.072-4.736 11.072-11.072s-5.536-11.872-11.872-11.872h-23.744c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.072 12.64 11.072z m176.384-46.656h-52.992a12.48 12.48 0 0 0-12.64 12.672v11.072h-35.584c-7.104 1.568-12.64 7.904-12.64 14.24s12.64 16.608 12.64 16.608h35.584v11.072c0 7.104 5.536 12.672 12.64 12.672h18.976v37.984c0 7.104 5.536 12.672 11.872 12.672s11.872-5.536 11.872-12.672V222.528h11.072a12.48 12.48 0 0 0 12.64-12.672V156.864a13.76 13.76 0 0 0-13.44-12.672z m0 657.312h-11.072v-35.584c-1.568-7.104-7.904-12.64-14.24-12.64-7.104 0-16.608 12.64-16.608 12.64v35.584h-11.072a12.48 12.48 0 0 0-12.64 12.64v18.976h-35.584c-6.336 0-11.872 5.536-11.872 11.872s5.536 11.872 11.872 11.872h35.584v11.072a12.48 12.48 0 0 0 12.64 12.64h52.992a12.48 12.48 0 0 0 12.64-12.64v-52.992c0-7.904-5.536-13.44-12.64-13.44z"
            p-id="14039"
          ></path>
        </svg>
      </span>
      <span @click="() => addRect()" :draggable="true" @dragend="onDragend('rect')">
        <svg
          t="1650855811131"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="18499"
          width="26"
          height="26"
        >
          <path
            d="M864 896H160a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32h704a32 32 0 0 1 32 32v704a32 32 0 0 1-32 32zM192 832h640V192H192v640z"
            p-id="18500"
          ></path>
        </svg>
      </span>
      <span @click="() => addCircle()" :draggable="true" @dragend="onDragend('circle')">
        <svg
          t="1650855860236"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="19440"
          width="26"
          height="26"
        >
          <path
            d="M512 928C282.624 928 96 741.376 96 512S282.624 96 512 96s416 186.624 416 416-186.624 416-416 416z m0-768C317.92 160 160 317.92 160 512s157.92 352 352 352 352-157.92 352-352S706.08 160 512 160z"
            p-id="19441"
          ></path>
        </svg>
      </span>
      <span @click="() => addTriangle()" :draggable="true" @dragend="onDragend('triangle')">
        <svg
          t="1650874633978"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2032"
          width="26"
          height="26"
        >
          <path
            d="M928.64 896a2.144 2.144 0 0 1-0.64 0H96a32.032 32.032 0 0 1-27.552-48.288l416-704c11.488-19.456 43.552-19.456 55.104 0l413.152 699.2A31.936 31.936 0 0 1 928.64 896zM152.064 832h719.84L512 222.912 152.064 832z"
            p-id="2033"
          ></path>
        </svg>
      </span>
      <!-- 多边形按钮 -->
      <span @click="() => addPolygon()" :draggable="true" @dragend="onDragend('polygon')">
        <svg
          t="1650874633978"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2032"
          width="26"
          height="26"
        >
          <path
            d="M161.152 398.016l134.016 412.416h433.664l134.016-412.416L512 143.104 161.152 398.08zM512 64l426.048 309.568-162.752 500.864H248.704L85.952 373.568 512 64z"
            p-id="2033"
          ></path>
        </svg>
      </span>
    </div>
    <Divider plain orientation="left">{{ $t('draw_elements') }}</Divider>
    <div class="tool-box">
      <span
        @click="drawingLineModeSwitch('line')"
        :class="state.isDrawingLineMode && state.lineType === 'line' && 'bg'"
      >
        <svg
          t="1673022047861"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="4206"
          width="20"
          height="20"
        >
          <path
            d="M187.733333 1024h-170.666666c-10.24 0-17.066667-6.826667-17.066667-17.066667v-170.666666c0-10.24 6.826667-17.066667 17.066667-17.066667h170.666666c10.24 0 17.066667 6.826667 17.066667 17.066667v170.666666c0 10.24-6.826667 17.066667-17.066667 17.066667zM34.133333 989.866667h136.533334v-136.533334H34.133333v136.533334zM1006.933333 204.8h-170.666666c-10.24 0-17.066667-6.826667-17.066667-17.066667v-170.666666c0-10.24 6.826667-17.066667 17.066667-17.066667h170.666666c10.24 0 17.066667 6.826667 17.066667 17.066667v170.666666c0 10.24-6.826667 17.066667-17.066667 17.066667zM853.333333 170.666667h136.533334V34.133333h-136.533334v136.533334z"
            fill=""
            p-id="4207"
          ></path>
          <path
            d="M187.733333 853.333333c-3.413333 0-10.24 0-13.653333-3.413333-6.826667-6.826667-6.826667-17.066667 0-23.893333l648.533333-648.533334c6.826667-6.826667 17.066667-6.826667 23.893334 0s6.826667 17.066667 0 23.893334l-648.533334 648.533333c0 3.413333-6.826667 3.413333-10.24 3.413333z"
            fill=""
            p-id="4208"
          ></path>
        </svg>
      </span>
      <span
        @click="drawingLineModeSwitch('arrow')"
        :class="state.isDrawingLineMode && state.lineType === 'arrow' && 'bg'"
      >
        <!-- <svg t="1673022047861" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4206" width="20" height="20"><path d="M187.733333 1024h-170.666666c-10.24 0-17.066667-6.826667-17.066667-17.066667v-170.666666c0-10.24 6.826667-17.066667 17.066667-17.066667h170.666666c10.24 0 17.066667 6.826667 17.066667 17.066667v170.666666c0 10.24-6.826667 17.066667-17.066667 17.066667zM34.133333 989.866667h136.533334v-136.533334H34.133333v136.533334zM1006.933333 204.8h-170.666666c-10.24 0-17.066667-6.826667-17.066667-17.066667v-170.666666c0-10.24 6.826667-17.066667 17.066667-17.066667h170.666666c10.24 0 17.066667 6.826667 17.066667 17.066667v170.666666c0 10.24-6.826667 17.066667-17.066667 17.066667zM853.333333 170.666667h136.533334V34.133333h-136.533334v136.533334z" fill="" p-id="4207"></path><path d="M187.733333 853.333333c-3.413333 0-10.24 0-13.653333-3.413333-6.826667-6.826667-6.826667-17.066667 0-23.893333l648.533333-648.533334c6.826667-6.826667 17.066667-6.826667 23.893334 0s6.826667 17.066667 0 23.893334l-648.533334 648.533333c0 3.413333-6.826667 3.413333-10.24 3.413333z" fill="" p-id="4208"></path></svg> -->
        <svg
          t="1673026778912"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2659"
          width="20"
          height="20"
        >
          <path
            d="M320 738.133333L827.733333 230.4l-29.866666-29.866667L290.133333 708.266667v-268.8h-42.666666v341.333333h341.333333v-42.666667H320z"
            fill="#444444"
            p-id="2660"
          ></path>
        </svg>
      </span>
      <span
        @click="drawingLineModeSwitch('thinTailArrow')"
        :class="state.isDrawingLineMode && state.lineType === 'thinTailArrow' && 'bg'"
      >
        <svg
          t="1715323097309"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="24572"
          width="20"
          height="20"
        >
          <path
            d="M485.954269 735.978673 643.38051 811.107852C688.831327 832.798434 686.17686 860.459274 635.3838 871.903075L81.378406 996.721881C31.19882 1008.027444-1.313538 976.266799 10.130269 925.473745L134.949081 371.468357C146.254656 321.288783 173.611855 317.095036 195.744311 363.471653L270.873453 520.897858 903.670271 62.052983C986.301645 2.136458 1004.805285 20.426857 944.799125 103.181838L485.954269 735.978673Z"
            fill="#444444"
            p-id="24573"
          ></path>
        </svg>
      </span>
    </div>
    <Divider plain orientation="left">{{ $t('hand_draw_elements') }}</Divider>
    <div class="tool-box">
      <span
        @click="handDrawingModelSwitch('handLine')"
        :class="state.isDrawingLineMode && state.lineType === 'handLine' && 'bg'"
      >
        <svg
          t="1716643794764"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="1464"
          width="32"
          height="32"
          xmlns:xlink="http://www.w3.org/1999/xlink"
        >
          <path
            d="M290.12096 721.0944c16.1856 16.01664 45.34912 14.24768 57.09184 2.61632 11.75808-11.61472 17.96352-40.01408 1.77024-56.03072L226.12992 546.176c-16.19328-16.01664-45.18144-13.11488-58.10304-0.35072-12.92416 12.79488-9.57312 45.05344 6.63552 61.07136L290.12096 721.0944 290.12096 721.0944z"
            fill="#272536"
            p-id="1465"
          ></path>
          <path
            d="M380.57216 648.18432c16.19328 16.03328 43.1872 16.52864 59.9872 1.13408 0 0 22.06464-20.23168 49.952-47.8144C773.59616 321.52064 919.36768 91.53664 864.7232 37.49376c-54.62656-54.03648-288.98688 88.32896-572.06528 368.31872-27.90272 27.58528-48.37248 49.39392-48.37248 49.39392-15.5712 16.608-15.06048 43.29856 1.1328 59.3152L380.57216 648.18432 380.57216 648.18432z"
            fill="#272536"
            p-id="1466"
          ></path>
          <path
            d="M245.8496 750.0672l-92.56448-91.54432c-16.20992-16.01792-36.8384-12.09216-45.85088 8.72576 0 0-68.8896 158.88512-49.90336 177.67936 18.60224 18.37824 179.46624-49.55392 179.46624-49.55392C258.0544 786.47296 262.02752 766.08384 245.8496 750.0672L245.8496 750.0672z"
            fill="#272536"
            p-id="1467"
          ></path>
          <path
            d="M180.704 980.48c-37.42848 0-59.9296-3.75552-60.8704-3.91552-4.29824-0.7296-8.07808-2.90688-10.91712-6.01216-12.80512-7.98976-20.97408-22.64192-20.97408-38.70976 0-16.07296 8.16896-30.72768 20.97792-38.71232 0.31872-0.34688 0.64896-0.6848 0.99328-1.01248 3.59296-3.40224 8.24064-5.25952 13.02656-5.25952 0.67712 0 1.3568 0.03712 2.0352 0.11264 0.06144 0.00768 0.5056 0.05504 1.30688 0.13312 1.47584-0.16256 2.95424-0.24448 4.42368-0.24448 3.51104 0 6.9952 0.45312 10.39232 1.34528 8.66816 0.5056 21.68576 1.04704 37.52704 1.04448 69.51552 0 199.62496-10.41024 289.98016-80.11008 42.44096-32.73984 100.52736-50.04672 167.9744-50.04672 53.25056 0 109.91872 10.82624 163.87968 31.31136 53.99936 20.4992 102.74816 49.7728 141.18912 84.76544 7.45984 3.86304 13.63584 9.88544 17.86624 17.41696 0.0896 0.09472 0.18048 0.18688 0.27136 0.28288 4.59008 4.83712 6.40256 11.488 5.43872 17.75488 0.16896 1.62048 0.25216 3.24736 0.25216 4.88192 0 24.80512-19.18208 44.9856-42.76224 44.9856-1.55392 0-3.10528-0.08704-4.64384-0.26496-1.02528 0.17536-2.06848 0.26496-3.12064 0.26496-0.1152 0-0.22912 0-0.3456-0.00256-5.18272-0.09728-10.11072-2.36288-13.69472-6.28992-5.42208-3.39968-10.12352-8.06784-13.66144-13.57056-31.86304-29.43744-113.27616-92.8448-223.04256-92.8448-53.21344 0-106.81472 15.10912-159.31392 44.90496-58.42688 33.15712-120.76032 57.35296-185.26848 71.91552C272.9536 975.13728 226.2144 980.48 180.704 980.48z"
            fill="#272536"
            p-id="1468"
          ></path>
        </svg>
      </span>
    </div>
  </div>
</template>

<script setup name="Tools">
import { v4 as uuid } from 'uuid';
// import initializeLineDrawing from '@/core/remove/initializeLineDrawing';
import { getPolygonVertices } from '@/utils/math';
import useSelect from '@/hooks/select';
import { useI18n } from 'vue-i18n';

// 默认属性
const defaultPosition = { shadow: '', fontFamily: 'arial' };
// 拖拽属性
const dragOption = {
  left: 0,
  top: 0,
};
const { t } = useI18n();
const { fabric, canvasEditor } = useSelect();
const state = reactive({
  isDrawingLineMode: false,
  lineType: false,
});
// let drawHandler = null;

const addText = (option) => {
  const text = new fabric.IText(t('everything_is_fine'), {
    ...defaultPosition,
    ...option,
    fontSize: 80,
    id: uuid(),
  });
  canvasEditor.canvas.add(text);
  if (!option) {
    text.center();
  }
  canvasEditor.canvas.setActiveObject(text);
};

// const addImg = (e) => {
//   const imgEl = e.target.cloneNode(true);
//   const imgInstance = new fabric.Image(imgEl, {
//     ...defaultPosition,
//     id: uuid(),
//     name: '图片default',
//   });
//   canvasEditor.canvas.add(imgInstance);
//   canvasEditor.canvas.renderAll();
// };

const addTextBox = (option) => {
  const text = new fabric.Textbox(t('everything_goes_well'), {
    ...defaultPosition,
    ...option,
    splitByGrapheme: true,
    width: 400,
    fontSize: 80,
    id: uuid(),
  });
  canvasEditor.canvas.add(text);
  if (!option) {
    text.center();
  }
  canvasEditor.canvas.setActiveObject(text);
};

const addTriangle = (option) => {
  const triangle = new fabric.Triangle({
    ...defaultPosition,
    ...option,
    width: 400,
    height: 400,
    fill: '#92706B',
    id: uuid(),
    name: '三角形',
  });
  canvasEditor.canvas.add(triangle);
  if (!option) {
    triangle.center();
  }
  canvasEditor.canvas.setActiveObject(triangle);
};

const addPolygon = (option) => {
  const polygon = new fabric.Polygon(getPolygonVertices(5, 200), {
    ...defaultPosition,
    ...option,
    fill: '#ccc',
    id: uuid(),
    name: '多边形',
  });
  polygon.set({
    // 创建完设置宽高，不然宽高会变成自动的值
    width: 400,
    height: 400,
    // 关闭偏移
    pathOffset: {
      x: 0,
      y: 0,
    },
  });
  canvasEditor.canvas.add(polygon);
  if (!option) {
    polygon.center();
  }
  canvasEditor.canvas.setActiveObject(polygon);
};

const addCircle = (option) => {
  const circle = new fabric.Circle({
    ...defaultPosition,
    ...option,
    radius: 150,
    fill: '#57606B',
    id: uuid(),
    name: '圆形',
  });
  canvasEditor.canvas.add(circle);
  if (!option) {
    circle.center();
  }
  canvasEditor.canvas.setActiveObject(circle);
};

const addRect = (option) => {
  const rect = new fabric.Rect({
    ...defaultPosition,
    ...option,
    fill: '#F57274',
    width: 400,
    height: 400,
    id: uuid(),
    name: '矩形',
  });
  canvasEditor.canvas.add(rect);
  if (!option) {
    rect.center();
  }
  canvasEditor.canvas.setActiveObject(rect);
};
const drawingLineModeSwitch = (type) => {
  state.lineType = type;
  state.isDrawingLineMode = !state.isDrawingLineMode;
  canvasEditor.setMode(state.isDrawingLineMode);
  canvasEditor.setLineType(type);
  canvasEditor.canvas.isDrawingMode = false;

  // this.canvasEditor.setMode(this.isDrawingLineMode);
  // this.canvasEditor.setArrow(isArrow);
  canvasEditor.canvas.forEachObject((obj) => {
    if (obj.id !== 'workspace') {
      obj.selectable = !state.isDrawingLineMode;
      obj.evented = !state.isDrawingLineMode;
    }
  });
};

const handDrawingModelSwitch = (type) => {
  state.lineType = type;
  state.isDrawingLineMode = !state.isDrawingLineMode;
  canvasEditor.setMode(state.isDrawingLineMode);
  canvasEditor.setLineType(type);

  canvasEditor.canvas.isDrawingMode = state.isDrawingLineMode;
  canvasEditor.canvas.freeDrawingBrush.width = 10;
  canvasEditor.canvas.forEachObject((obj) => {
    if (obj.id !== 'workspace') {
      obj.selectable = !state.isDrawingLineMode;
      obj.evented = !state.isDrawingLineMode;
    }
  });
};

// 拖拽开始时就记录当前打算创建的元素类型
const onDragend = (type) => {
  // todo 拖拽优化 this.canvas.editor.dragAddItem(event, item);
  switch (type) {
    case 'text':
      addText(dragOption);
      break;
    case 'textBox':
      addTextBox(dragOption);
      break;
    case 'rect':
      addRect(dragOption);
      break;
    case 'circle':
      addCircle(dragOption);
      break;
    case 'triangle':
      addTriangle(dragOption);
      break;
    case 'polygon':
      addPolygon(dragOption);
      break;
    default:
  }
};

onMounted(() => {
  nextTick(() => {
    // 线条绘制
    // drawHandler = initializeLineDrawing(canvasEditor.canvas, defaultPosition);

    canvasEditor.canvas.on('drop', (opt) => {
      // 画布元素距离浏览器左侧和顶部的距离
      const offset = {
        left: canvasEditor.canvas.getSelectionElement().getBoundingClientRect().left,
        top: canvasEditor.canvas.getSelectionElement().getBoundingClientRect().top,
      };

      // 鼠标坐标转换成画布的坐标（未经过缩放和平移的坐标）
      const point = {
        x: opt.e.x - offset.left,
        y: opt.e.y - offset.top,
      };

      // 转换后的坐标，restorePointerVpt 不受视窗变换的影响
      const pointerVpt = canvasEditor.canvas.restorePointerVpt(point);
      dragOption.left = pointerVpt.x;
      dragOption.top = pointerVpt.y;
    });
  });
});
</script>

<style scoped lang="less">
.tool-box {
  display: flex;
  justify-content: space-around;

  span {
    flex: 1;
    text-align: center;
    padding: 5px 0;
    background: #f6f6f6;
    margin-left: 2px;
    cursor: pointer;

    &:hover {
      background: #edf9ff;

      svg {
        fill: #2d8cf0;
      }
    }
  }

  .bg {
    background: #ff980078;

    &:hover {
      svg {
        fill: #2d8cf0;
      }
    }
  }
}

.img {
  width: 20px;
}
</style>
