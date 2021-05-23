<template lang="pug">.
    section#signup
        .title
            |注册
        .content
            el-input(placeholder="名称")
            el-input(placeholder="邮箱")
            el-input(placeholder="密码")
            el-button(type="primary" @click="submit")
                |提交
</template>

<script lang="ts" setup>
    import { ref } from "vue"
    import * as api from "@/api"

    const username = ref('')
    const email = ref('')
    const password = ref('')

    const submit = () =>
    {
        api.signup({
            username: username.value,
            email: email.value,
            password: password.value,
        })
    }
</script>

<style lang="stylus">
#signup
    width 100%
    >.content
        display flex
        flex-direction column
        row-gap 8px
</style>