export interface BaseDto {
    id: number;
    _links?: {
        self: { href: string };
        update?: { href: string };
        delete?: { href: string };
    };
}