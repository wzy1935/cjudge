<template>
  <div>
    <div class=" mx-auto max-w-3xl mt-10 p-4 bg-white">
      <div class=" flex justify-between">
        <div></div>
        <div class=" cursor-pointer flex" @click="help=!help">
          <div class=" border-2 border-blue-600 text-white bg-blue-600 px-4 py-1">开启教程</div>
          <div class=" border-2 border-blue-600  px-4 py-1"
          :class="help ? 'text-white bg-blue-600' : 'text-blue-600'">{{help ? '开' : '关'}}</div>
        </div>
      </div>
    </div>
    <div class=" mx-auto max-w-3xl mb-10 p-4 bg-gray-50 flex flex-col space-y-2">
      <div v-show="help" class=" p-4 rounded-md bg-gray-200 flex flex-col space-y-2">
        <p>欢迎使用Cjudge！本教程将引导您如何使用它。</p>
        <p>Cjudge的评测方式非常简单，您只需要准备3份文件即可进行评测。它们分别是您自己想要测试的代码，用来对拍的代码，以及用于生成样例的代码。下面以加法器A+B为例介绍，您可以直接粘贴示例代码体验功能：</p>
      </div>
      <p class=" text-lg">1-标准答案：</p>
      <div v-show="help" class=" p-4 rounded-md bg-gray-200 flex flex-col space-y-2">
        <p>在此处您可以填写用来对拍的代码作为标准答案。这里您需要收集一份已有的标准答案，确保能获得正确输出。例如对A+B来说：</p>
        <pre class=" bg-white m-2 overflow-auto">
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLong() + scanner.nextLong());
    }

}
        </pre>
        <p>注意：本测试仅能评测java程序，且类名必须为Main。</p>
        <p>登录后可使用仓库功能，您可以使用其他用户上传的答案进行评测。当然，您无法获取他人上传的源码。</p>
      </div>
      <base-code v-model="standard"></base-code>
      <p class=" text-lg">2-样例生成器：</p>
      <div v-show="help" class=" p-4 rounded-md bg-gray-200 flex flex-col space-y-2">
        <p>在此处您需要填写用来生成样例的脚本作为样例生成器。同样需要是java文件且为Main类。例如生成A+B样例的样例生成器：</p>
        <pre class=" bg-white m-2 overflow-auto">
public class Main {
    public static void main(String[] args) {
        System.out.println(rnd(0,Integer.MAX_VALUE-1) + " " + rnd(0,Integer.MAX_VALUE-1));
    }

    public static int rnd(int from, int to) {
        return (int) (from + Math.random() * (to - from + 1));
    }
}
        </pre>
        <p>登录后您能够获取问题自带的样例生成器。您也可以自己手动添加。</p>
      </div>
      <base-code v-model="generator"></base-code>
      <p class=" text-lg">3-待测代码：</p>
      <div v-show="help" class=" p-4 rounded-md bg-gray-200 flex flex-col space-y-2">
        <p>在此处填写您需要测试的代码。同样需要为Main类。假设您需要测试下面这段代码结果是否正确：</p>
        <pre class=" bg-white m-2 overflow-auto">
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt() + scanner.nextInt());
    }

}
        </pre>
      </div>
      <base-code v-model="test"></base-code>
      <div v-show="help" class=" p-4 rounded-md bg-gray-200 flex flex-col space-y-2">
        <p>完成这三部分的填写以后点击按钮即可进入测试页。测试页面能够提供非常完备的测试信息。</p>
        <p>以上仅为试用功能。获取完整体验，请注册/登录。</p>
      </div>
      <div class=" text-center">
        <button class=" bg-blue-600 px-4 py-2 text-white m-4 w-2/3" @click="$router.push('/local/result/' + 20 + '/' + getTime())">开始测试</button>
      </div>
    </div>
    
  </div>
</template>


<script>
import BaseCode from '../components/utils/BaseCode.vue'

export default {
  components: {
    BaseCode
  },
  data: function() {
    return {
      help: false,
    }
  },
  computed: {
    generator: {
      get() {
        return this.$store.state.local.generator;
      },
      set(value) {
        this.$store.commit('setLocalGenerator', value);
      }
    },
    test: {
      get() {
        return this.$store.state.local.test;
      },
      set(value) {
        this.$store.commit('setLocalTest', value);
      }
    },
    standard: {
      get() {
        return this.$store.state.local.standard;
      },
      set(value) {
        this.$store.commit('setLocalStandard', value);
      }
    }
  },
  methods: {
    getTime() {
      let date = new Date();
      return date.getTime();
    }
  }
}
</script>
