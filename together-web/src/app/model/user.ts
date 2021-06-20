export class User {
  public id: number;
  public phone: string;
  public email: string;
  public password: string;
  public firstName: string;
  public middleName: string;
  public lastName: string;
  public verificationCode: string;
  public isActive: number;
  public isReported: number;
  public isBlocked: number;
  public createdAt: Date;
  public updatedAt: Date;

  constructor() { }
}
