import { Page, Locator } from '@playwright/test';

export class FormPage {
  readonly page: Page;
  readonly nameInput: Locator;
  readonly passwordInput: Locator;
  readonly disabledInput: Locator;
  readonly readonlyInput: Locator;
  readonly colorSelect: Locator;
  readonly submitButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.nameInput = page.locator('#my-name-id');
    this.passwordInput = page.locator('#my-password-id');
    this.disabledInput = page.locator("xpath=.//input[@name='my-disabled']");
    this.readonlyInput = page.locator("xpath=.//input[@name='my-readonly']");
    this.colorSelect = page.locator("select[name='my-select']");
    this.submitButton = page.locator('#submit-form');
  }

  async goto() {
    await this.page.goto('https://d3pv22lioo8876.cloudfront.net/tiptop/');
  }

  async submitForm(name: string, password: string) {
    await this.nameInput.fill(name);
    await this.passwordInput.fill(password);
    await this.submitButton.click();
  }
}
