import axios from 'axios';
import {settings} from '@/Variables'

import {Team} from '@/model/Team';

export namespace Api {
    class Api {
        constructor(public endpoint?: string) {
        }

        apiClient = axios.create({
            baseURL: settings.BASE_URL + this.endpoint,
            responseType: 'json',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        async get<T>(url: string): Promise<T> {
            const response = await this.apiClient.get<T>(url);
            return response.data;
        };
    }

    export module Teams {
        const api = new Api('/teams');

        export function getTeams(): Promise<Team[]> {
            return api.get<Team[]>('');
        }
    }
}

