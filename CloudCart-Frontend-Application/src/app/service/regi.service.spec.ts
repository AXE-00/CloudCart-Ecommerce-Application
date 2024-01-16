import { TestBed } from '@angular/core/testing';

import { RegiService } from './regi.service';

describe('RegiService', () => {
  let service: RegiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
