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
    import {Team} from "@/model/Team";
    import {TeamApi} from "@/services/TeamApi";

    @Component
    export default class HelloWorld extends Vue {
        @Prop() private msg!: string;
        private teamApi: TeamApi = new TeamApi();

        teams: Team[] = [];

        private loadTeams(): void {
            this.teamApi.getAll()
                .then((teams) => this.teams = teams)
                .catch((error) => console.log(`Sorry, but ${error} happened :/`))
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
