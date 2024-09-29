import { Component } from '@angular/core';
import {MatGridList, MatGridTile} from "@angular/material/grid-list";
import {MatCard, MatCardContent} from "@angular/material/card";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-choose-group',
  standalone: true,
  imports: [
    MatGridList,
    MatGridTile,
    MatCard,
    MatCardContent,
    NgForOf
  ],
  templateUrl: './study-group.component.html',
  styleUrl: './study-group.component.scss'
})
export class StudyGroupComponent {

  loadedGroups = ["7.C","8.A","8.B","8.C","9.A","9.B",
    "9.C","10.A","10.B","10.C","11.A","11.B"];
}
