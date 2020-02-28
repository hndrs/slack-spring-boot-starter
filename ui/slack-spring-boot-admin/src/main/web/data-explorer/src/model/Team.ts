export class Team {
    constructor(public bot: Team.Bot,
                public teamName: string,
                public teamId?: string,
                public incomingWebhook?: Team.IncomingWebhook) {
    }
}

export namespace Team {
    export class IncomingWebhook {
        constructor(channel: string,
                    channelId: string,
                    configurationUrl: string,
                    url: string) {
        }
    }

    export class Bot {
        constructor(userId: string,
                    accessToken: string) {
        }
    }
}