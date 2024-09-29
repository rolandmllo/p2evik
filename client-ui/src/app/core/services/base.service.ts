import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments";
import {BaseDto} from "../../shared/models/BaseDto";

@Injectable({
  providedIn: 'root'
})
export abstract class BaseService<T extends BaseDto> {
  protected apiUrl: string

  protected constructor(protected http: HttpClient, protected apiEndpoint: string) {
    this.apiUrl = environment.backendUrl + environment.apiPrefix + apiEndpoint;
  }

  getById(id: number): Observable<T> {
    return this.http.get<T>(`${this.apiUrl}/${id}`);
  }

  getAll(): Observable<T[]> {
    return this.http.get<T[]>(this.apiUrl);
  }

  create(item: T): Observable<T> {
    return this.http.post<T>(this.apiUrl, item);
  }

  update(item: T, updateLink?: string): Observable<T> {
    const url = updateLink ? updateLink : `${this.apiUrl}/${(item as T).id}`;
    return this.http.put<T>(url, item);
  }

  delete(deleteLink: string | number): Observable<void> {
    const url = typeof deleteLink === 'string' ? deleteLink : `${this.apiUrl}/${deleteLink}`;
    return this.http.delete<void>(url);
  }
}
