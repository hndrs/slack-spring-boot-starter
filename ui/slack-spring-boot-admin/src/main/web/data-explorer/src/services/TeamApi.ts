import {Api} from "@/services/Api";
import {Team} from '@/model/Team';

export class TeamApi extends Api<Team> {

    constructor() {
        super('/teams');
    }
}
