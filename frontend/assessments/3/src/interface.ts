export interface IAddOn {
  name: string;
  cost: string;
  currency: string;
}

export interface IHotel {
  id: number;
  name: string;
  costPerNight: string;
  currency: string;
  addOns: IAddOn[];
}

export interface HotelsSliceState{
  hotelsList: IHotel[],
  state: "fulfilled" | "pending" | "rejected",
  error: string
}

export interface TitleProps {
  readonly title: string
}

export interface CardProps {
  readonly type: string,
  readonly hotel: IHotel,
  readonly selectedHotel: IHotel | null,
  readonly setSelectedHotel: React.Dispatch<React.SetStateAction<IHotel | null>>
}

export interface AddOnProps {
  readonly addOn: IAddOn,
  readonly selectedAddOns: IAddOn[],
  readonly setSelectedAddOns: React.Dispatch<React.SetStateAction<IAddOn[]>>
}