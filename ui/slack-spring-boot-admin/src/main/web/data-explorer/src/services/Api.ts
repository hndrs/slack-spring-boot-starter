import axios from 'axios';
import {settings} from '@/Variables'

export class Api<T> {
    protected constructor(public uri: string) {
    }

    protected apiClient = axios.create({
        baseURL: settings.BASE_URL + this.uri,
        responseType: 'json',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    getAll(): Promise<T[]> {
        return this.apiClient.get<T[]>('').then((result) => result.data)
    }

}