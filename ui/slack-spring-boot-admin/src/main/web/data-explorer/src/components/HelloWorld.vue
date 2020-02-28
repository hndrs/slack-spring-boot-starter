<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <p>
            {{ teams }}
        </p>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {Api} from '@/services/Api'
    import {Team} from "@/model/Team";

    @Component
    export default class HelloWorld extends Vue {
        @Prop() private msg!: string;
        teams: Team[] = [];

        private loadTeams(): void {
            Api.Teams.getTeams().then(teams => {
                this.teams = teams;
            }).catch(error => {
                console.log(error);
            })
        }

        mounted() {
            this.loadTeams();
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
