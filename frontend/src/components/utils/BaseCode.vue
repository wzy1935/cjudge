<template>
  <div>
    <codemirror v-model="code" :options="cmOptions" @input="$emit('change', code)" />
  </div>
</template>

<style>
.CodeMirror {
  height: 24rem;
  /* height: auto; */
}

/* .CodeMirror-line span {
  font-family:Consolas;
} */

.CodeMirror-scroll {
  /* min-height: 50rem;  */
}
</style>

<script>
import { codemirror } from "vue-codemirror";
import "codemirror/lib/codemirror.css";
import "codemirror/theme/idea.css";
import "codemirror/addon/edit/closebrackets.js";
import "codemirror/mode/clike/clike.js";
import "codemirror/addon/selection/active-line.js";

export default {
  model: {
    prop: 'code',
    event: 'change'
  },
  components: {
    codemirror,
  },
  data: function () {
    return {
      code: "",
      cmOptions: {
        tabSize: 4,
        mode: "text/x-java",
        theme: "idea",
        lineNumbers: true,
        autoCloseBrackets: true,
        indentUnit: 4,
        smartIndent: true,
        line: true,
        //scrollbarStyle: 'null'
        styleActiveLine: true,

      },
    };
  },
};
</script>